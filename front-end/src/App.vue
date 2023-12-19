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
const pictureUserId = ref(null);


// Get all visible photos with axios
const getPhotos = async () => {
  const data = await axios.get("http://localhost:8080/api/photos");
  photos.value = data.data
}


// Mounted
onMounted(getPhotos);
</script>

<template>
  <div v-if="successSendMessage" class="container alert alert-success mt-3">
    <span>Messaggio inviato correttamente</span>
  </div>
  <PhotoIndex v-if="!sendingMessage" :photos="photos" @sendMessage="sendingMessage = true"
    @userId="userIdFromChild => pictureUserId = userIdFromChild" />
  <MessageForm v-if="sendingMessage" @back="sendingMessage = false" @sendSuccess="successSendMessage = true"
    :userId="pictureUserId" />
</template>

<style scoped></style>
