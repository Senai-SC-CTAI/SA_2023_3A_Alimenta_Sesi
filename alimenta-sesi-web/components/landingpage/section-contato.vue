<template>
  <div class="flex flex-col md:flex-row justify-center items-center">
    <span class="hidden md:inline-block">
    <img class="m-10 h-[30vw]" src="../../assets/img/contato.png" alt="" />
    </span>

    <div class=" md:w-auto flex flex-col justify-center items-center">
      <h2 id="sectionContato" class="text-secondary font-bold text-4xl">
        Entre em contato
      </h2>

      <div class="bg-[#FF4E02] h-1 w-32 rounded-xl m-2" />

      <div class="w-full flex flex-col justify-between gap-5 md:flex-row">
        <div class="w-full relative my-2">
          <input type="email" class="basic-input" />
          <label
            class="input-label absolute left-3 top-3 transition-transform transform origin-top text-gray-500 text-xs"
          >
            E-mail
          </label>
        </div>
        <div class="w-full relative my-2">
          <input type="text" class="basic-input" />
          <label
            class="input-label absolute left-3 top-3 transition-transform transform origin-top text-gray-500 text-xs"
          >
            Nome
          </label>
        </div>
      </div>
      <div class="w-full relative my-5">
        <textarea type="text" class="basic-input h-[140px]" />
        <label
          class="input-label absolute left-3 top-3 transition-transform transform origin-top text-gray-500 text-xs"
        >
          Nome
        </label>
      </div>
      <button class="bg-primary font-bold text-white px-10 rounded-2xl p-2">
        Enviar
      </button>
    </div>
  </div>
</template>

<style scoped>
.input-field:focus + .input-label {
  @apply -translate-y-5 bg-white px-1;
}

.basic-input {
  @apply input-field w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-primary focus:ring-primary;
}
</style>
