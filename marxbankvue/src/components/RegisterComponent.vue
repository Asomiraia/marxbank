<template>
  <div>
    <form action="" class="flex flex-col w-full items-center">
      <div class="input-style">
        <label for="username">Username</label>
        <input
          id="username"
          v-model="username"
          type="text"
          name="username"
          :disabled="authStatus === 'loading'" />
      </div>
      <div class="input-style">
        <label for="email">Email</label>
        <input
          id="email"
          v-model="email"
          type="email"
          name="email"
          :disabled="authStatus === 'loading'" />
      </div>
      <div class="input-style">
        <label for="password">Password</label>
        <input
          id="password"
          v-model="password"
          type="password"
          name="password"
          :disabled="authStatus === 'loading'" />
      </div>
      <div class="input-style">
        <label for="repeatPassword">Repeat password</label>
        <input
          id="repeatPassword"
          v-model="repeatPassword"
          type="password"
          name="repeatPassword"
          :disabled="authStatus === 'loading'" />
      </div>
      <button
        class="
          mx-auto
          my-5
          w-2/5
          bg-white
          rounded-sm
          drop-shadow-md
          relative
          h-16
          block
          border-2 border-white
          hover:border-2 hover:border-red-500
          duration-300
        "
        @click.prevent="register">
        <img
          src="/Hammer.svg"
          :class="
            authStatus === 'loading'
              ? 'communismIcon right-0 loading'
              : 'communismIcon right-0 transform-gpu rotate-90'
          " />
        <p class="inline-block font-bold text-2xl z-10 relative">Register</p>
      </button>
    </form>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import { mapGetters, mapActions } from "vuex";
import { SignUpRequest } from "../types/types";

export default defineComponent({
  name: "RegisterComponent",
  computed: {
    ...mapGetters(["authStatus"]),
  },
  data() {
    return {
      username: "",
      email: "",
      password: "",
      repeatPassword: "",
    };
  },
  methods: {
    ...mapActions(["signup"]),
    register(): void {
      if (this.password !== this.repeatPassword) {
        //TODO: gjør noe fornuftig
        console.log("passwords dont match");
        return;
      }
      const request: SignUpRequest = {
        username: this.username,
        password: this.password,
        email: this.email,
      };

      this.signup(request)
        .then(() => this.$router.push("/"))
        .catch((err) => console.log(err));
    },
  },
});
</script>
<style scoped>
.input-style {
  @apply flex flex-col m-1 mt-5 items-start w-3/5;
}

.input-style label {
  @apply text-red-500 text-xl font-bold;
}

.input-style input {
  @apply w-full h-8 p-1 rounded-sm drop-shadow-sm;
}

.communismIcon {
  @apply w-12 absolute top-3 z-0 origin-center;
}

.communismIcon {
  @apply w-12 absolute top-3 z-0 origin-center;
}

@keyframes newCommunsim {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(-360deg);
  }
}

.loading {
  animation: newCommunsim 1s linear forwards infinite;
  animation-play-state: running;
}
</style>
