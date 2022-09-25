package ru.job4j.cash;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class AccountStorageTest {
    @Test
    void whenAdd() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        Account firstStorage = storage.getById(1).
                orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstStorage.amount()).isEqualTo(100);
    }


    @Test
    void whenUpdate() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.update(new Account(1, 200));
        Account firstStorage = storage.getById(1).
                orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstStorage.amount()).isEqualTo(200);
    }

    @Test
    void whenDelete() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.delete(1);
        assertThat(storage.getById(1)).isEmpty();
    }

    @Test
    void whenTransfer() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 100));
        storage.transfer(1, 2, 100);
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        var secondAccount = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount.amount()).isEqualTo(0);
        assertThat(secondAccount.amount()).isEqualTo(200);
    }

    @Test
    void whenTransferNoToAccount() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        Account toAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(storage.transfer(1, 2, 100)).isFalse();
        assertThat(toAccount.amount()).isEqualTo(100);
    }

    @Test
    void whenTransferNoFromAccount() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        Account fromAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(storage.transfer(2, 1, 100)).isFalse();
        assertThat(fromAccount.amount()).isEqualTo(100);
    }

    @Test
    void whenTransferIllegalAmountFalse() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 100));
        Account toAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        Account fromAccount = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 2"));
        assertThat(storage.transfer(1, 2, 500)).isFalse();
        assertThat(toAccount.amount()).isEqualTo(100);
        assertThat(fromAccount.amount()).isEqualTo(100);
    }

    @Test
    void whenTransferIllegalAmountTrue() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 100));
        Account toAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        Account fromAccount = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 2"));
        assertThat(storage.transfer(1, 2, 50)).isTrue();
        assertThat(toAccount.amount()).isEqualTo(100);
        assertThat(fromAccount.amount()).isEqualTo(100);
    }
}