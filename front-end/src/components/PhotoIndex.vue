<script setup>
// IMPORT dependency
import { defineProps } from 'vue';
import { ref } from 'vue';

// DATA
const search = ref('');
const filteredPhotos = ref();

// PROPS
const props = defineProps({
    photos: {
        type: Array,
        required: true
    }
});

// Functions
const filterPhotos = () => {
    search.value = search.value.toLowerCase();
    filteredPhotos.value = props.photos.filter(photo => photo.title.toLowerCase().includes(search.value));
}
</script>

<template>
    <h1 class="text-center my-3">Photo-Album</h1>
    <div class="container">
        <label for="search" class="me-3">Cerca la tua foto per nome </label>
        <input @keyup="filterPhotos" type="search" id="search" v-model="search">
    </div>
    <div class="container">
        <div class="row mt-3">
            <div v-if="!filteredPhotos" v-for="photo in photos" class="col-4">
                <div class="card">
                    <img :src="photo.photoUrl" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">{{ photo.title }}</h5>
                        <p class="card-text">{{ photo.description }}</p>
                        <p>
                            <span class="fw-bold">Categorie: </span>
                            <span v-for="cat in photo.categories">
                                {{ cat.title + " | " }}
                            </span>
                        </p>
                    </div>
                </div>
            </div>

            <div v-if="filteredPhotos" v-for="photo in filteredPhotos" class="col-4">
                <div class="card">
                    <img :src="photo.photoUrl" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">{{ photo.title }}</h5>
                        <p class="card-text">{{ photo.description }}</p>
                        <p>
                            <span class="fw-bold">Categorie: </span>
                            <span v-for="cat in photo.categories">
                                {{ cat.title + " | " }}
                            </span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<style></style>