<template>
  <div class="flex h-screen w-screen justify-center items-center">
    <div class="bg-white">
      <img src="./assets/img/404 Error.png" class="" />
    </div>
    <div class="flex flex-col ml-2">
      <h1 class="text-primary text-lg font-bold">Ops...</h1>
      <p class="text-primary">
        Página não disponível. Pode ser que alguém tenha comido o código.
      </p>
      <p class="text-primary">Estamos resolvendo isso!</p>
      <div>
        <button @click='useRouter()'
        class="bg-secondary font-bold text-white rounded-md shadow-lg py-2 pe-16 ps-16 px-8 mt-2 "
      >
        Pagina inicial
      </button>
      </div>
    </div>
  </div>
</template>

