import { AccountState } from "@/store/modules/accounts/types";
import { RootState } from "@/store/types";
import { getters } from "../store/modules/accounts/getters";
import { mutations } from "../store/modules/accounts/mutations";

const rootState: RootState = {
  message: "",
};

const testState: AccountState = {
  accountStatus: "",
  accounts: [
    {
      id: 1,
      userId: 1,
      name: "test",
      accNumber: 200,
      balance: 200,
      interest: 3.0,
      type: "Sparekonto",
    },
    {
      id: 2,
      userId: 2,
      name: "test2",
      accNumber: 201,
      balance: 200,
      interest: 3.0,
      type: "Sparekonto",
    },
  ],
};

describe("getters", () => {
  it("test get all accounts", () => {
    const actual = getters.allAccounts(testState, null, rootState, null);

    expect(actual).toEqual(testState.accounts);
  });
  it("test filter accounts by name", () => {
    const empty = getters.filterAccountsByName(
      testState,
      null,
      rootState,
      null
    )("");

    expect(empty).toEqual([]);

    const test = getters.filterAccountsByName(
      testState,
      null,
      rootState,
      null
    )("test");

    expect(test).toEqual(testState.accounts);

    const test2 = getters.filterAccountsByName(
      testState,
      null,
      rootState,
      null
    )("test2");

    expect(test2).toEqual([testState.accounts[1]]);
  });
  it("test filter accounts by user id", () => {
    const empty = getters.filterAccountsByUserId(
      testState,
      null,
      rootState,
      null
    )(3);

    expect(empty).toEqual([]);

    const actual = getters.filterAccountsByUserId(
      testState,
      null,
      rootState,
      null
    )(2);

    expect(actual).toEqual([testState.accounts[1]]);
  });
  it("test filter accounts by user id and name", () => {
    const empty1 = getters.filterAccountsByUserIdAndName(
      testState,
      null,
      rootState,
      null
    )(3, "test");
    const empty2 = getters.filterAccountsByUserIdAndName(
      testState,
      null,
      rootState,
      null
    )(1, "test2");

    expect(empty1).toEqual([]);
    expect(empty2).toEqual([]);

    const actual = getters.filterAccountsByUserIdAndName(
      testState,
      null,
      rootState,
      null
    )(1, "test");

    expect(actual).toEqual([testState.accounts[0]]);
  });
  it("test get account by id", () => {
    const noMatch = getters.getAccountById(testState, null, rootState, null)(3);

    expect(noMatch).toBeUndefined();

    const actual = getters.getAccountById(testState, null, rootState, null)(1);

    expect(actual).toEqual(testState.accounts[0]);
  });
});

describe("mutations", () => {
  it("test set accounts", () => {
    const newAccounts = [
      {
        id: 1,
        userId: 1,
        name: "test",
        accNumber: 200,
        balance: 200,
        interest: 3.0,
        type: "Sparekonto",
      },
    ];

    mutations.setAccounts(testState, newAccounts);

    expect(testState.accounts).toEqual(newAccounts);
  });

  it("test add account", () => {
    const newAccount = {
      id: 2,
      userId: 1,
      name: "test2",
      accNumber: 201,
      balance: 200,
      interest: 3.0,
      type: "Sparekonto",
    };

    const oldAccounts = testState.accounts;

    mutations.addAccount(testState, newAccount);

    expect(testState.accounts).toEqual([...oldAccounts, newAccount]);
  });
});
