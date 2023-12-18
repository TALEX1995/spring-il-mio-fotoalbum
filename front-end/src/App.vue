<script setup>
// Import dependency
import { onMounted, ref } from 'vue';
import axios from 'axios';

// Import components
import PhotoIndex from './components/PhotoIndex.vue';
import MessageForm from './components/MessageForm.vue';

// Data
const photos = ref(null);
const sendingMessage = ref(false);
const successSendMessage = ref(false);


// Get all visible photos with axios
const getPhotos = async () => {
  const data = await axios.get("http://localhost:8080/api/photos");
  photos.value = data.data
}


// Mounted
onMounted(getPhotos);
</script>

<template>
  <div class="container d-flex justify-content-end mt-2">
    <button class="btn btn-primary" @click="sendingMessage = true" v-if="!sendingMessage">Invia messaggio
      all'amministratore della pagina</button>
  </div>
  <div v-if="successSendMessage" class="container alert alert-success mt-3">
    <span>Messaggio inviato correttamente</span>
  </div>
  <PhotoIndex v-if="!sendingMessage" :photos="photos" />
  <MessageForm v-if="sendingMessage" @back="sendingMessage = false" @sendSuccess="successSendMessage = true" />
</template>

<style scoped></style>
