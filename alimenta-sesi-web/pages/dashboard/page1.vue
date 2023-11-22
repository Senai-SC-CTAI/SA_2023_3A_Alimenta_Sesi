<template>
    <div>
      <div v-if="isFullScreen" class="full-screen-content">
        
        <h1>Página Cheia</h1>
        <form v-if="showPasswordForm" @submit.prevent="checkPassword">
          <label for="password">Senha:</label>
          <input type="password" v-model="password" required />
          <button type="submit">Confirmar</button>
        </form>
        <p v-if="passwordError">{{ passwordError }}</p>
      </div>
      <div v-else>
        <h1>Página Não Cheia</h1>
      </div>
    </div>
  </template>
  
  <script setup >
  import { ref, onMounted } from 'vue';
  
  const isFullScreen = ref(false);
  const showPasswordForm = ref(false);
  const password = ref('');
  const passwordError = ref('');
  
  onMounted(() => {
    enterFullScreen();
  });
  
  const enterFullScreen = () => {
    isFullScreen.value = true;
    showPasswordForm.value = true;
  
    const element = document.documentElement;
  
    if (element.requestFullscreen) {
      element.requestFullscreen();
    } else if (element['webkitRequestFullscreen']) {
      element['webkitRequestFullscreen']();
    }
  };
  
  const checkPassword = () => {

    if (password.value === '1234') {

      exitFullScreen();
    } else {
      passwordError.value = 'Senha incorreta. Tente novamente.';
    }
  };
  
  const exitFullScreen = () => {
    isFullScreen.value = false;
    showPasswordForm.value = false;
  

    if (document.exitFullscreen) {
      document.exitFullscreen();
    } else if (document['webkitExitFullscreen']) {
      document['webkitExitFullscreen'](); 
    }
  };
  </script>
  
  <style scoped>
  .full-screen-content {
    height: 100vh;
    width: 100vw;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #f0f0f0;
    color: #333;
    font-family: 'Arial', sans-serif;
  }
  </style>
  