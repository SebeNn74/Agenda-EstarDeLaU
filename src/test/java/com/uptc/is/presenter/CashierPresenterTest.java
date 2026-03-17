package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.view.contracts.ICashierView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;

class CashierPresenterTest {

    private CashierRepository repo;
    private ICashierView view;
    private CashierPresenter presenter;

    @BeforeEach
    void setUp(){

        repo = mock(CashierRepository.class);
        view = mock(ICashierView.class);

        when(repo.getAll()).thenReturn(List.of());

        presenter = new CashierPresenter(repo,view);
    }

    @Test
    void shouldCreateCashierSuccessfully(){

        when(view.getNuipInput()).thenReturn("123");
        when(view.getStudentCodeInput()).thenReturn("456");
        when(view.getNamesInput()).thenReturn("Juan");
        when(view.getSurnamesInput()).thenReturn("Perez");
        when(view.getTelNumberInput()).thenReturn("12345");
        when(view.getEmailInput()).thenReturn("test@mail.com");

        when(repo.searchById("123")).thenReturn(Optional.empty());

        presenter.createCashier();

        verify(repo).create(any(Cashier.class));
    }

    @Test
    void shouldShowErrorIfCashierExists(){

        when(view.getNuipInput()).thenReturn("123");
        when(view.getStudentCodeInput()).thenReturn("456");
        when(view.getNamesInput()).thenReturn("Juan");
        when(view.getSurnamesInput()).thenReturn("Perez");
        when(view.getTelNumberInput()).thenReturn("12345");
        when(view.getEmailInput()).thenReturn("test@mail.com");

        when(repo.searchById("123")).thenReturn(Optional.of(new Cashier()));

        presenter.createCashier();

        verify(view).displayError(anyString());
    }

    @Test
    void shouldNotCreateIfNuipEmpty(){

        when(view.getNuipInput()).thenReturn("");

        presenter.createCashier();

        verify(view).displayError(anyString());
    }
}
