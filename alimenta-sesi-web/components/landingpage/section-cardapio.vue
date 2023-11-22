<template>
  <div class="flex flex-col justify-center items-center">
    <h2
      id="sectionCardapio"
      class="text-secondary font-bold text-4xl text-secondary"
    >
      Cardápio
    </h2>
    <div class="bg-primary h-1 w-32 rounded-xl my-2"></div>
    <div
      class="w-4/5 md:w-3/5 grid grid-cols-2 lg:grid-cols-5 grid-rows-2 gap-4 mt-10 m-auto"
    >
      <div class="lg:row-span-2 col-span-2 h-full w-full">
        <swiper
          :pagination="swiperPagination"
          :modules="modules"
          class="swiper-container bg-[#f4f4f4] w-full h-[212px] lg:h-[440px] rounded-3xl"
        >
          <swiper-slide
            v-for="(slide, slideIndex) in cardapio[0].slides"
            :key="slideIndex"
          >
            <div class="card">
              <img :src="slide.image" :alt="slide.title" />
              <p>{{ slide.subtitle }}</p>          
            </div>
          </swiper-slide>
          <div class="swiper-pagination absolute bottom-2"></div>
          <div class="bg-secondary h-6 absolute bottom-0 w-full"></div>
        </swiper>
      </div>

      <div class="w-full col-span-2 lg:col-span-3">
        <swiper
          :pagination="swiperPagination"
          :modules="modules"
          class="swiper-container bg-[#f4f4f4] w-full h-[212px] rounded-3xl"
        >
          <swiper-slide
            v-for="(slide, slideIndex) in cardapio[1].slides"
            :key="slideIndex"
          >
            <div class="card">
              
               <img :src="slide.image" :alt="slide.title" />
              <p>{{ slide.subtitle }}</p>
            </div>
          </swiper-slide>
          <div class="swiper-pagination absolute bottom-2"></div>
          <div class="bg-secondary h-6 absolute bottom-0 w-full"></div>
        </swiper>
      </div>
      <div class="w-full col-span-2 lg:col-span-3">
        <swiper
          :pagination="swiperPagination"
          :modules="modules"
          class="swiper-container bg-[#f4f4f4] w-full h-[212px] rounded-3xl"
        >
          <swiper-slide
            v-for="(slide, slideIndex) in cardapio[2].slides"
            :key="slideIndex"
          >
            <div class="card">
              
                <img :src="slide.image" :alt="slide.title" />
              <p>{{ slide.subtitle }}</p>
            
            </div>
          </swiper-slide>
          <div class="swiper-pagination absolute bottom-2"></div>
          <div class="bg-secondary h-6 absolute bottom-0 w-full"></div>
        </swiper>
      </div>
    </div>
    <!-- <video></video> -->
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";

import "swiper/css";
import "swiper/css/pagination";

import { Pagination } from "swiper/modules";

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    const modules = [Pagination];

    const cardapio = [
      {
        title: "Salgados",
        slides: [
          {
            title: "Dogão",
            subtitle: "R$ 6,00",
            image: "/path/to/your/image1.jpg",
            
          },
          {
            title: "Pão de queijo",
            subtitle: "R$ 6,00",
            image: "/path/to/your/image2.jpg",
          },
          {
            title: "Coxinha",
            subtitle: "R$ 6,00",
            image: "/path/to/your/image3.jpg",
          },
        ],
      },
      {
        title: "Bebida",
        slides: [
          {
            title: "Suco",
            subtitle: "R$ 8,00",
            image: "/path/to/your/image4.jpg",
          },
          {
            title: "Nescau",
            subtitle: "R$",
            image: "/path/to/your/image5.jpg",
          },
          {
            title: "Àgua",
            subtitle: "R$",
            image: "/path/to/your/image6.jpg",
          },
        ],
      },
      {
        title: "doce",
        slides: [
          {
            title: "Brownie",
            subtitle: "R$ 8,00",
            image: "./",
          },
          {
            title: "croissant de chocolate",
            subtitle: "R$ 6,00",
            image: "/path/to/your/image8.jpg",
          },
          {
            title: "croissant dois amores",
            subtitle: "R$",
            image: "/path/to/your/image9.jpg",
          },
        ],
      },
    ];

    const swiperPagination = {
      dynamicBullets: true,
    };

    return {
      modules,
      cardapio,
      swiperPagination,
    };
  },
};
</script>

<style scoped>
.card {
  padding: 20px;
  text-align: center;
  border-radius: 8px;
}

.swiper-container {
  position: relative;
}

.swiper-pagination {
  bottom: 12px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
