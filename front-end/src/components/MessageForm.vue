<script setup>
// IMPORT dependency
import { defineProps, defineEmits, ref } from 'vue'
import axios from 'axios';

// DATA
const newMessage = ref({
    email: null,
    messageText: null,
});

// PROPS
const props = defineProps({
    userId: {
        type: Number
    }
});

// EMITS
const emits = defineEmits(["back", "sendSuccess"]);

// Function
const submit = async () => {
    const data = await axios.post(
        `http://localhost:8080/api/messages?userId=${props.userId}`,
        newMessage.value
    );

    emits("sendSuccess")
    emits("back");

    return
}
</script>


<template>
    <div class="container">
        <h1 class="text-center">Contatta l'utente della foto</h1>
        <form @submit.prevent="submit">
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" placeholder="name@example.com"
                    v-model="newMessage.email">
            </div>
            <div class="mb-3">
                <label for="message" class="form-label">Messaggio</label>
                <textarea rows="3" class="form-control" v-model="newMessage.messageText" id="message"></textarea>
            </div>

            <button class="btn btn-secondary" type="button" @click="$emit('back')">Torna alle foto</button>
            <button class="btn btn-primary ms-4" type="submit">Invia</button>
        </form>
    </div>
</template>

<style></style>