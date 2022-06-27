import React from "react";
import "../index.css";
import { MdCheckBoxOutlineBlank, MdCheckBox, MdRemoveCircleOutline } from "react-icons/md";
import cn from "classnames";
import { onToggle, onRemove } from "./../store";
import { useDispatch } from "react-redux";
const TodoListItem = ({ todo }) => {
    const {id, text, checked} = todo;
    let dispatch = useDispatch();
    return (
    <div className="TodoListItem" onClick={()=>{
        dispatch(onToggle(id))
    }}>
        <div className={cn("checkbox", {checked})}>
            {checked ? <MdCheckBox /> : <MdCheckBoxOutlineBlank />}
            <div className="text">{text}</div>
        </div>
        <div className="remove" onClick={(e)=>{
            dispatch(onRemove(id));
            e.stopPropagation()
        }}>
            <MdRemoveCircleOutline />
        </div>
    </div>
);
};
export default TodoListItem;
