<template>
  <div class="flex flex-row justify-center items-center">
    <div class="hidden text-center h-screen w-screen md:flex flex-col justify-center items-center bg-secondary">
      <img src="../assets/img/login.png" alt="" />
      <p class="font-semibold text-white text-1xl">
        Conecte-se à cozinha do sabor e sirva nossos clientes com excelência!
      </p>
    </div>
    <div class="h-screen flex flex-col items-center justify-center gap-3 text-center bg-white w-[80rem]">
      <h2 class="font-bold text-center text-primary text-4xl mb-10">Seja bem-vindo!</h2>
      <div class="md:hidden w-60 object-contain">
        <img class="rounded-full" src="../assets/img/contato.png" alt="" />
      </div>
      <div class="relative">
        <input v-model="cpf" v-maska data-maska="['###.###.###-##']" type="cpf" class="basic-input mb-1" />
        <label class="input-label absolute left-3 top-3 transition-transform transform origin-top text-gray-500 text-xs">CPF</label>
      </div>
      <div class="relative">
        <input v-model="senha" type="password" class="basic-input" />
        <label class="input-label absolute left-3 top-3 transition-transform transform origin-top text-gray-500 text-xs">Senha</label>
      </div>
      <button @click="fazerLogin" class="bg-primary py-2.5 pe-16 ps-16 px-10 font-bold text-white rounded-xl shadow-lg mt-10">Login</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const cpf = ref('');
const senha = ref('');
const router = useRouter();

const fazerLogin = async () => {
  try {
    console.log('Enviando dados:', { cpf: cpf.value, senha: senha.value });

    const response = await axios.post('http://localhost:8080/api/usuario/login', {
  cpf: cpf.value.replace(/\D/g, ''),
  senha: senha.value
});

    if (response.data === 'Login bem-sucedido!') {
      console.log('Login bem-sucedido!');
      router.push('/dashboard/page1');
    } else {
      console.error('Credenciais inválidas');
    }
  } catch (error) {
    console.error('Erro ao fazer login:', error);
  }
};
</script>


<style scoped>
.input-field:focus + .input-label {
  @apply -translate-y-5 bg-white px-1;
}

.basic-input {
  @apply input-field w-full border border-gray-300 rounded-md py-2 px-10 focus:outline-none focus:border-primary focus:ring-primary;
}
</style>
