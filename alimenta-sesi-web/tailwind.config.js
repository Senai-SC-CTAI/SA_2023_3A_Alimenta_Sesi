/** @type {import('tailwindcss').Config} */
export default {
  purge: ['./pages/**/*.vue', './components/**/*.vue'],
  darkMode: false,
    content: [],
    theme: {
      extend: {
        colors:
        {
          primary: '#FF4E02',
          secondary: '#3FD261'
        },
      },
    },
    plugins: [],
  }