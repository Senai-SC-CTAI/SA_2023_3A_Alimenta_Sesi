<template>
  <div class="bg-[#FCFBF7]">
    <div class="flex justify-center items-center">
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
        <button @click="mostrarFormulario" class="bg-primary text-white p-2 mt-4">Cadastrar Novo Produto</button>
        <!-- Formulário para cadastrar novo produto -->
        <div v-if="formularioVisivel" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div class="bg-white p-8 rounded-md">
            <h2 class="text-2xl font-bold">Cadastrar Novo Produto</h2>
            <form @submit.prevent="cadastrarProduto">
              <!-- Adicione os campos do formulário conforme necessário -->
              <label for="nome">Nome: </label>
              <input v-model="novoProduto.nome" required id="nome">

              <label for="descricao">Descrição: </label>
              <input v-model="novoProduto.descricao" required id="descricao">

              <label for="categoria">Categoria: </label>
              <input v-model="novoProduto.categoria" required id="categoria">

              <label for="calorias">Calorias: </label>
              <input v-model="novoProduto.calorias" required type="number" id="calorias">

              <label for="preco">Preço: </label>
              <input v-model="novoProduto.preco" required type="number" id="preco">

              <label for="imagem">Imagem: </label>
              <input v-model="novoProduto.imagem" required id="imagem">

              <button type="submit">Cadastrar</button>
              <button @click="fecharFormulario">Cancelar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

interface Statistic {
  label: string;
  value: number;
}

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

const statistics = ref<Statistic[]>([]);
const formularioVisivel = ref(false);
const novoProduto = ref<Produto>({
  id: 0,
  nome: '',
  descricao: '',
  categoria: '',
  calorias: 0,
  preco: 0,
  imagem: '',
  quantidadeDisponivel: 0,
});

const fetchStatistics = async () => {
  try {
    const mediaCaloriasResponse = await axios.get<string>('http://localhost:8080/api/produtos/estatisticas/media-calorias');
    const mediaCalorias = parseFloat(mediaCaloriasResponse.data);

    const categoriasResponse = await axios.get<[string, number][]>('http://localhost:8080/api/produtos/estatisticas/categorias');
    const categorias = categoriasResponse.data;

    statistics.value = [
      { label: 'Produtos no total', value: categorias.reduce((acc, [, count]) => acc + count, 0) },
      { label: 'Média de calorias', value: mediaCalorias },
      { label: 'Produtos salgados', value: categorias.find(([categoria]) => categoria === 'salgados')?.[1] || 0 },
      { label: 'Produtos doces', value: categorias.find(([categoria]) => categoria === 'doces')?.[1] || 0 },
      { label: 'Produtos Bebidas', value: categorias.find(([categoria]) => categoria === 'bebidas')?.[1] || 0 },
      { label: 'Produtos outros', value: categorias.find(([categoria]) => categoria === 'outros')?.[1] || 0 },
    ];
  } catch (error) {
    console.error('Erro ao buscar estatísticas:', error);
  }
};

const mostrarFormulario = () => {
  formularioVisivel.value = true;
};

const fecharFormulario = () => {
  formularioVisivel.value = false;
  // Limpar os campos do novo produto após fechar o formulário
  novoProduto.value = {
    id: 0,
    nome: '',
    descricao: '',
    categoria: '',
    calorias: 0,
    preco: 0,
    imagem: '',
    quantidadeDisponivel: 0,
  };
};

const cadastrarProduto = async () => {
  try {
    await axios.post('http://localhost:8080/api/produtos', novoProduto.value);
    console.log('Produto cadastrado com sucesso:', novoProduto.value);
    fetchStatistics(); // Atualizar estatísticas após o cadastro
    fecharFormulario(); // Fechar o formulário após o cadastro
  } catch (error) {
    console.error('Erro ao cadastrar o produto:', error);
  }
};

onMounted(fetchStatistics);
</script>
