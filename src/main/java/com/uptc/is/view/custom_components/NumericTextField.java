package com.uptc.is.view.custom_components;

import javax.swing.*;
import javax.swing.text.*;
import java.text.NumberFormat;
import java.util.Locale;

public class NumericTextField extends JTextField {

    public NumericTextField(int maxDigits) {
        super();
        setDocument(new FormattedNumericDocument(this, maxDigits));
    }

    private static class FormattedNumericDocument extends PlainDocument {
        private final JTextField textField;
        private final int maxDigits;

        public FormattedNumericDocument(JTextField textField, int maxDigits) {
            this.textField = textField;
            this.maxDigits = maxDigits;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null || !str.matches("\\d+")) return;

            String currentText = getText(0, getLength()).replace(".", "");
            int digitOffset = countDigitsBeforeOffset(getText(0, getLength()), offset);

            StringBuilder newRaw = new StringBuilder(currentText);
            newRaw.insert(digitOffset, str);

            if (newRaw.length() > maxDigits) return;

            formatAndUpdateText(newRaw.toString(), digitOffset + str.length());
        }

        @Override
        public void remove(int offset, int length) throws BadLocationException {
            String currentText = getText(0, getLength()).replace(".", "");
            int digitOffset = countDigitsBeforeOffset(getText(0, getLength()), offset);

            if (digitOffset < 0 || digitOffset >= currentText.length()) return;

            StringBuilder newRaw = new StringBuilder(currentText);
            newRaw.delete(digitOffset, Math.min(digitOffset + length, newRaw.length()));

            formatAndUpdateText(newRaw.toString(), digitOffset);
        }

        private void formatAndUpdateText(String rawText, int digitsBeforeCaret) throws BadLocationException {
            if (rawText.isEmpty()) {
                super.remove(0, getLength());
                return;
            }

            long value = Long.parseLong(rawText);
            String formatted = NumberFormat.getInstance(Locale.GERMANY).format(value);

            int newCaretPos = getOffsetForDigitIndex(formatted, digitsBeforeCaret);

            super.remove(0, getLength());
            super.insertString(0, formatted, null);

            SwingUtilities.invokeLater(() -> textField.setCaretPosition(Math.min(newCaretPos, textField.getText().length())));
        }

        private int countDigitsBeforeOffset(String textWithDots, int offset) {
            int count = 0;
            for (int i = 0; i < offset && i < textWithDots.length(); i++) {
                if (Character.isDigit(textWithDots.charAt(i))) {
                    count++;
                }
            }
            return count;
        }

        private int getOffsetForDigitIndex(String formatted, int digitIndex) {
            int count = 0;
            for (int i = 0; i < formatted.length(); i++) {
                if (Character.isDigit(formatted.charAt(i))) {
                    if (count == digitIndex) return i;
                    count++;
                }
            }
            return formatted.length();
        }

    }

    public String getNumericString() {
        return super.getText().replace(".", "");
    }

}
