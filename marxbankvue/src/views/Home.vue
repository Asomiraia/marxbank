<template class="text-gray-600 body-font">
  <div class="min-w-[60%]">
    <br />
    <main class="text-on-same-line space-x-36">
      <div
        class="
          relative
          w-full
          h-48
          bg-cover bg-center
          group
          rounded-lg
          overflow-hidden
          shadow-lg
          transition
          duration-300
          ease-in-out
        "
        style="
          background-image: url('https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/f868ecef-4b4a-4ddf-8239-83b2568b3a6b/de7hhu3-3eae646a-9b2e-4e42-84a4-532bff43f397.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2Y4NjhlY2VmLTRiNGEtNGRkZi04MjM5LTgzYjI1NjhiM2E2YlwvZGU3aGh1My0zZWFlNjQ2YS05YjJlLTRlNDItODRhNC01MzJiZmY0M2YzOTcuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.R0h-BS0osJSrsb1iws4-KE43bUXHMFvu5PvNfoaoi8o');
        ">
        <div
          class="
            absolute
            inset-0
            bg-green-500 bg-opacity-75
            transition
            duration-300
            ease-in-out
          " />
        <div class="relative w-full h-full px-4 sm:px-6 lg:px-4 items-center">
          <div class="mt-16">
            <p class="text-white text-2xl mt-2 space-x-2 font-bold">
              Totalbeløp på konto:
            </p>
            <p class="text-white text-2xl mt-2 font-bold">
              <!---- Sett inn logikk under><-->
              kr 25 000
            </p>
          </div>
        </div>
      </div>

      <div class="w-full">
        <RecentTransaction
          v-for="transaction in allTransactions.slice(0, 1)"
          :key="transaction.amount"
          :date="transaction.date"
          :from="transaction.from"
          :to="transaction.to"
          :amount="transaction.amount"
          class="transaction" />
      </div>
    </main>

    <br />

    <main class="text-on-same-line space-x-32">
      <div>
        <div class="container">
          <h1 class="text-3xl md:text-2xl font-bold mb-3">Mine favoritter</h1>
          <div>
            <MyFavorites class="min-w-max" />
          </div>
        </div>
      </div>

      <div class="container">
        <img
          class="image"
          src="https://img.huffingtonpost.com/asset/5bb675e7250000940039a5e0.jpeg?ops=scalefit_720_noupscale&format=webp"
          alt="" />
        <div class="text-left text-gray-800 mt-2">
          <h2>Vil du vite hvor mye pensjon du kan</h2>
          <h2>forvente å ha? Eller ønsker du å spare</h2>
          <h2>opp til egen bolig?</h2>
        </div>
        <router-link to="/calculator">
          <button
            class="
              bg-green-500
              hover:bg-green-400
              text-white
              font-bold
              py-2
              px-4
              border-b-4 border-green-700
              hover:border-green-500
              rounded
              mt-6
            ">
            Prøv sparekalkulatoren!
          </button>
        </router-link>
      </div>
    </main>
  </div>
</template>

<script>
import MyFavorites from "../components/MyFavorites.vue";
import RecentTransaction from "../components/RecentTransaction.vue";
import { mapGetters } from "vuex";

export default {
  name: "Home",
  computed: mapGetters(["allTransactions"]),
  components: {
    MyFavorites,
    RecentTransaction,
  },
};
</script>

<style>
.container {
  min-width: 300px;
  max-width: 700px;
  margin: 12px auto;
  overflow: auto;
  height: 330px;
  border: 1px solid rgb(132, 133, 134);
  padding: 10px;
  border-radius: 5px;
  background-color: white;
}
.image {
  max-width: 300px;

  max-height: 200px;
  border-radius: 2px;
}
.text-on-same-line {
  display: flex;
  justify-content: space-between;
}
</style>
