<template>
  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 overflow-y-auto">
    <div v-for="produto in produtos" :key="produto.id" @click="mostrarDetalhes(produto)" class="bg-white">
      <img :src="produto.imagem" alt="food" class="w-full h-40 object-cover">
      <div class="flex flex-row justify-between items-center p-4">
        <h2 class="font-bold">{{ produto.nome }}</h2>
        <p class="text-orange-600">{{ produto.quantidadeDisponivel }}</p>
      </div>
    </div>

    <!-- Modal de Detalhes do Produto -->
    <div v-if="produtoSelecionado" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div class="bg-white p-8 rounded-md">
        <h2 class="text-2xl font-bold">{{ modoEdicao ? 'Editar Produto' : produtoSelecionado.nome }}</h2>

        <form v-if="modoEdicao" @submit.prevent="salvarEdicao">
          <label for="nome">Nome: </label>
          <input v-model="produtoSelecionado.nome" required id="nome">
          <label for="descricao">Descrição: </label>
          <input v-model="produtoSelecionado.descricao" required id="descricao">

          <label for="categoria">Categoria: </label>
          <input v-model="produtoSelecionado.categoria" required id="categoria">

          <label for="calorias">Calorias: </label>
          <input v-model="produtoSelecionado.calorias" required type="number" id="calorias">

          <label for="preco">Preço: </label>
          <input v-model="produtoSelecionado.preco" required type="number" id="preco">

          <label for="imagem">Imagem: </label>
          <input v-model="produtoSelecionado.imagem" required id="imagem">



          <button type="submit">Salvar</button>
          <button @click="cancelarEdicao">Cancelar</button>
        </form>

        <div v-else>
          <p>{{ produtoSelecionado.descricao }}</p>
          <p>Categoria: {{ produtoSelecionado.categoria }}</p>
          <p>Calorias: {{ produtoSelecionado.calorias }}</p>
          <p>Preço: {{ produtoSelecionado.preco }}</p>
          <p>Quantidade Disponível: {{ produtoSelecionado.quantidadeDisponivel }}</p>

          <button @click="ativarEdicao">Editar</button>
          <button @click="excluirProduto(produtoSelecionado)">Excluir</button>
        </div>

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

const produtos = ref<Produto[]>([]);
const produtoSelecionado = ref<Produto | null>(null);
const modoEdicao = ref(false);

const fetchProdutos = async () => {
  try {
    const response = await axios.get<Produto[]>('http://localhost:8080/api/produtos');
    produtos.value = response.data.map(produto => ({ ...produto }));
  } catch (error) {
    console.error('Erro ao buscar produtos:', error);
  }
};

const mostrarDetalhes = (produto: Produto) => {
  produtoSelecionado.value = produto;
  modoEdicao.value = false;
};

const fecharDetalhes = () => {
  produtoSelecionado.value = null;
  modoEdicao.value = false;
};

const excluirProduto = async (produto: Produto) => {
  const confirmacao = window.confirm('Tem certeza que deseja excluir este produto?');
  if (confirmacao) {
    try {
      await axios.delete(`http://localhost:8080/api/produtos/${produto.id}`);
      fecharDetalhes();
    } catch (error) {
      console.error('Erro ao excluir o produto:', error);
    }
  }
};

const salvarEdicao = async () => {
  try {
    await axios.put(`http://localhost:8080/api/produtos/${produtoSelecionado.value.id}`, produtoSelecionado.value);
    console.log('Produto editado com sucesso:', produtoSelecionado.value);
    fetchProdutos();
    fecharDetalhes();
  } catch (error) {
    console.error('Erro ao editar o produto:', error);
  }
};

const ativarEdicao = () => {
  modoEdicao.value = true;
};

const cancelarEdicao = () => {
  modoEdicao.value = false;
};

onMounted(fetchProdutos);
</script>
