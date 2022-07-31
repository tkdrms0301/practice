import { configureStore, createSlice } from '@reduxjs/toolkit';
import { useState } from 'react';

let movies = createSlice({
    name: 'movies',
});

export default configureStore({
    reducer: {},
});
