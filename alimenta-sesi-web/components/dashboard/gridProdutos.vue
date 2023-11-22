<template>
    <div class="bg-[#FCFBF7]">
      <div class="flex flex-row justify-center items-center">
        <!-- Estatísticas -->
        <div class="flex flex-col">
          <div class="flex flex-row font-bold text-6xl">
            <h1 class="text-primary">Alimenta</h1>
            <h1 class="text-secondary">Sesi</h1>
          </div>
          <div class="grid grid-rows-3 grid-flow-col gap-4 text-secondary">
            <div v-for="statistic in statistics" :key="statistic.label" class="bg-white text-center">
              <h2 class="font-bold">{{ statistic.value }}</h2>
              <p>{{ statistic.label }}</p>
            </div>
          </div>
        </div>
  
        <!-- Produtos -->
        <div class="grid grid-rows-3 grid-flow-col gap-4">
          <div v-for="produto in produtos" :key="produto.id" class="bg-white">
            <img :src="produto.imagem" alt="food" class="w-full h-40 object-cover">
            <div class="flex flex-row justify-between items-center p-4">
              <h2 class="font-bold">{{ produto.nome }}</h2>
              <p class="text-orange-600">{{ produto.quantidadeDisponivel }}</p>
            </div>
            <button @click="mostrarDetalhes(produto)">Ver Detalhes</button>
          </div>
        </div>
      </div>
  
      <!-- Modal de Detalhes do Produto -->
      <div v-if="produtoSelecionado" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
        <div class="bg-white p-8 rounded-md">
          <h2 class="text-2xl font-bold">{{ produtoSelecionado.nome }}</h2>
          <p>{{ produtoSelecionado.descricao }}</p>
          <p>Categoria: {{ produtoSelecionado.categoria }}</p>
          <p>Calorias: {{ produtoSelecionado.calorias }}</p>
          <p>Preço: {{ produtoSelecionado.preco }}</p>
          <p>Quantidade Disponível: {{ produtoSelecionado.quantidadeDisponivel }}</p>
          <button @click="fecharDetalhes">Fechar</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  interface Produto {
    id: number;
    nome: string;
    descricao: string;
    categoria: string;
    calorias: number;
    preco: number;
    imagem: string;
    quantidadeDisponivel: number;
  }
  
  interface Statistic {
    label: string;
    value: number;
  }
  
  const produtos = ref<Produto[]>([]);
  const produtoSelecionado = ref<Produto | null>(null);
  const statistics = ref<Statistic[]>([]);
  
  const fetchStatistics = async () => {
    // Implemente a lógica para buscar as estatísticas, semelhante ao que você fez antes.
  };
  
  const fetchProdutos = async () => {
    try {
      const response = await axios.get<Produto[]>('http://localhost:8080/api/produtos');
      produtos.value = response.data.map(produto => ({
        ...produto,
        // Adicione outras transformações, se necessário
      }));
    } catch (error) {
      console.error('Erro ao buscar produtos:', error);
    }
  };
  
  const mostrarDetalhes = (produto: Produto) => {
    produtoSelecionado.value = produto;
  };
  
  const fecharDetalhes = () => {
    produtoSelecionado.value = null;
  };
  
  onMounted(() => {
    fetchStatistics();
    fetchProdutos();
  });
  </script>
  
  <style scoped>
  /* Adicione estilos conforme necessário */
  </style>
  