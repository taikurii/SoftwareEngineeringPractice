package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        //equivalence class, valid entry (less than balance), not a a border case
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //equivalence class, invalid entry (greater than balance), not a border case
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(300));

        //equivalence class, invalid entry (negative amount), not a border case
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.withdraw(-50));

        //border cases not present might be -1, 0, 200, 201 (given that balance is 200)

    }

    @Test
    void isEmailValidTest(){
        //all equivalence class
        //valid entry
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //invalid entry, no email
        assertFalse( BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid("a@@b.com"));
        //invalid entry, hyphen before @
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //invalid entry, two periods next to each other
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //invalid entry, # present
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc##def@mail.com"));
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //invalid entry, # present
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //valid entry
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        //valid entry
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        //valid entry
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        //valid entry, border case because 2 characters after suffix "."
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        //equivalence class - invalid email(valid prefix, invalid suffix) - border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //equivalence class - invalid email(valid prefix, invalid suffix) - not a border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));

        //anything with a ".", "_", or "-" could technically be considered a border case because just one character
        // away from a double special character, but given that a suffix must have a ".", wouldn't they all be
        //border cases????

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}