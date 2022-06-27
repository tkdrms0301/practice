import { configureStore, createSlice } from "@reduxjs/toolkit";
var nextId = 4;
let todos = createSlice({
 name: "todos",
 initialState: [
  { id: 1, text: "리액트의 기초 알아보기", checked: true },
  { id: 2, text: "컴포넌트 스타일링해 보기", checked: true },
  { id: 3, text: "일정 관리 앱 만들어 보기", checked: false },
 ],
    reducers: {
        onToggle(state, action) {
       console.log(state);
       console.log(action);
       return state.map((todo) =>
        todo.id === action.payload ? { ...todo, checked: !todo.checked } : todo
       );
      },
        onInsert(state, action) {
            const todo = {
                id: nextId,
                text: action.payload,
                checked: false,
            };
            nextId += 1;
            return state.concat(todo);
        },
        onRemove(state, action) {
            const id = action.payload;
            return state.filter((todo) => todo.id !== id);
        },
    },
});
export default configureStore({
 reducer: {
    todos : todos.reducer,
 },
});

export let { onToggle, onInsert, onRemove } = todos.actions;
