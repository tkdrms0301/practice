import React from "react";
import "../index.css";
import TodoListItem from "./TodoListItem";
import { useSelector } from "react-redux"

const TodoList=({ onRemove, onToggle })=>{
    let {todos} = useSelector ((state) => {
        return state;
    });
    return(
        <div className="TodoList">
            {todos.map((todo)=>(<TodoListItem todo={todo} key={todo.id} onRemove={onRemove} onToggle={onToggle}></TodoListItem>
            ))}
        </div>
    );
};
export default TodoList;