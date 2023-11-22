export default {
  modules: [
    'nuxt-purgecss',
    'nuxt-swiper',
    ['nuxt-purgecss', { /* module options */ }],
    '@nuxtjs/tailwindcss', 
  ],
  css: [
    '~/assets/style/tailwind.css'
  ],
  build: {
    postcss: {
      postcssOptions: require('./postcss.config.js')
    }
  },
  devtools: {
    enabled: true
  },
  head: {
    title: 'AlimentaSesi',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'Descrição do Meu Projeto Nuxt.js' },
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
    ],
  },
 }
 