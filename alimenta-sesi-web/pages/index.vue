<template>
  <navbarLanding />
  <sectionHome class="my-20" />
  <sectionApp />
  <sectionCardapio class="my-10" />
  <sectionVideo />
  <sectionContato class="my-10" />
  <footerLanding />
</template>

<script setup lang="ts">
import navbarLanding from "@/components/landingpage/navbar-landing.vue";
import sectionHome from "@/components/landingpage/section-home.vue";
import sectionApp from "@/components/landingpage/section-app.vue";
import sectionCardapio from "@/components/landingpage/section-cardapio.vue";
import sectionVideo from "@/components/landingpage/section-video.vue";
import sectionContato from "@/components/landingpage/section-contato.vue";
import footerLanding from "@/components/landingpage/footer-Landing.vue";
</script>
