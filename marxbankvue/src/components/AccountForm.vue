<template>
  <div class="w-1/4">
    <h1 class="title">Ny konto</h1>
    <h1>Kontonavn</h1>
    <input
      ref="accountName"
      type="text"
      className="input"
      placeholder="Ny konto" />
    <h1>Kontotype</h1>
    <select
      id="accountTypes"
      ref="accountType"
      class="select"
      name="accountTypes">
      <option value="Brukskonto">Brukskonto</option>
      <option value="Sparekonto">Sparekonto</option>
      <option value="Marxkonto">Marxkonto</option>
      <option value="Kredittkonto">Kredittkonto</option>
    </select>
    <button class="button" @click="createAccountWrapper">Opprett konto</button>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "AccountForm",
  methods: {
    ...mapActions(["createAccount"]),
    createAccountWrapper() {
      const request = {
        name: this.$refs.accountName.value,
        type: this.$refs.accountType.value,
      };
      this.createAccount(request);
    },
  },
};
</script>

<style></style>
