<template>
  <div class="w-screen h-screen">
    <h1 class="text-red-500 font-bold text-4xl">The Revolutionaries' Bank</h1>
    <div
      class="
        flex
        bg-gray-500
        w-1/4
        h-auto
        mx-auto
        mt-40
        mb-auto
        drop-shadow-lg
        rounded-md
        flex-col
      ">
      <div v-if="!register">
        <form action="" class="flex flex-col w-full items-center" method="POST">
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
            <label for="password">Password</label>
            <input
              id="password"
              v-model="password"
              type="password"
              name="password"
              :disabled="authStatus === 'loading'" />
          </div>
          <button
            class="
              mt-5
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
            @click.prevent="requestLogin">
            <img
              src="/Sickle.svg"
              alt=""
              :class="
                authStatus === 'loading'
                  ? 'communismIcon left-1 loading'
                  : 'communismIcon left-1'
              " />
            <p class="inline-block font-bold text-2xl z-10 relative">Login</p>
          </button>
        </form>
        <button
          id="createNewUser"
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
          @click.prevent="createNewUser">
          <img
            src="/Hammer.svg"
            class="communismIcon right-0 transform-gpu rotate-90" />
          <p class="inline-block font-bold text-2xl z-10 relative">Register</p>
        </button>
      </div>
      <div v-if="register">
        <RegisterComponent />
        <button
          v-if="register"
          id="back"
          class="
            mx-auto
            mb-5
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
          @click="back">
          <p class="font-bold text-2xl z-10 relative">Back</p>
        </button>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import RegisterComponent from "./RegisterComponent.vue";
import { mapGetters, mapActions } from "vuex";
import { LoginRequest } from "../types/types";

export default defineComponent({
  name: "LoginComponent",
  components: {
    RegisterComponent,
  },
  computed: {
    ...mapGetters(["authStatus"]),
  },
  data() {
    return {
      username: "",
      password: "",
      register: false,
    };
  },
  methods: {
    ...mapActions(["login"]),
    requestLogin(): void {
      const request: LoginRequest = {
        username: this.username,
        password: this.password,
      };
      console.log(request);

      this.login(request)
        .then(() => this.$router.push("/"))
        .catch((err: any) => console.log(err));
    },

    createNewUser(): void {
      this.register = true;
    },

    back(): void {
      this.register = false;
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
