<template>
    <nav class="fixed top-0 left-0 right-0 bg-[#f4f4f4f4] rounded-full m-5 flex justify-center w-[350px] mx-auto p-3 z-10 cursor-pointer">
      <ul class="flex list-none">
        <li v-for="section in sections" :key="section.id">
          <a
            @click="scrollToAndSelect(section.id)"
            :class="{ 'font-bold text-secondary cursor-pointer': selectedNavItem === section.id }"
            class="mx-2 text-secondary hover:cursor-pointer"
          >
            {{ section.label }}
          </a>
        </li>
      </ul>
    </nav>
  </template>
  
  <script setup lang="ts">
  const selectedNavItem = ref<string>('');
  const sections = [
    { id: 'sectionHome', label: 'Home' },
    { id: 'sectionApp', label: 'App' },
    { id: 'sectionCardapio', label: 'Cardápio' },
    { id: 'sectionVideo', label: 'Video' },
    { id: 'sectionContato', label: 'Contato' },
  ];
  
  const scrollToAndSelect = (sectionId: string): void => {
    const section = document.getElementById(sectionId);
    if (section) {
      section.scrollIntoView({ behavior: 'smooth' });
      selectedNavItem.value = sectionId;
    }
  };
  </script>
  