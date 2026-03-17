package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.model.repository.ScheduleRepository;
import com.uptc.is.view.contracts.IScheduleView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;

class SchedulePresenterTest {

    private ScheduleRepository scheduleRepo;
    private CashierRepository cashierRepo;
    private IScheduleView view;
    private SchedulePresenter presenter;

    @BeforeEach
    void setUp(){

        scheduleRepo = mock(ScheduleRepository.class);
        cashierRepo = mock(CashierRepository.class);
        view = mock(IScheduleView.class);

        when(cashierRepo.getAll()).thenReturn(List.of());

        presenter = new SchedulePresenter(scheduleRepo,cashierRepo,view);
    }

    @Test
    void shouldCreateSchedule(){

        when(view.getNuipInput()).thenReturn("123");
        when(view.getDateInput()).thenReturn(LocalDate.now());
        when(view.getStartTimeInput()).thenReturn(LocalTime.of(8,0));
        when(view.getEndTimeInput()).thenReturn(LocalTime.of(10,0));

        Cashier cashier = new Cashier("123","456");

        when(cashierRepo.searchById("123")).thenReturn(Optional.of(cashier));

        presenter.createSchedule();

        verify(scheduleRepo).create(any());
    }

    @Test
    void shouldFailIfTimeInvalid(){

        when(view.getNuipInput()).thenReturn("123");
        when(view.getDateInput()).thenReturn(LocalDate.now());
        when(view.getStartTimeInput()).thenReturn(LocalTime.of(10,0));
        when(view.getEndTimeInput()).thenReturn(LocalTime.of(8,0));

        presenter.createSchedule();

        verify(view).displayError(anyString());
    }
}
