<template>
  <div class="flex flex-row">
    <Estatisticas />
    <ListaProdutos />
    <EdicaoProduto v-if="modalEdicaoAtivo" :produto="produtoSelecionado" @fechar="fecharEdicao" />
  </div>
</template>

<script setup lang="ts">
  import Estatisticas from '@/components/dashboard/Estatisticas.vue';
  import ListaProdutos from '@/components/dashboard/ListaProdutos.vue';

  const modalEdicaoAtivo = ref(false); // Certifique-se de declarar modalEdicaoAtivo aqui
  const produtoSelecionado = ref(null);

  const fecharEdicao = () => {
    modalEdicaoAtivo.value = false;
  };
</script>
