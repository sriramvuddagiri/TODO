import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Todo } from 'src/app/todo';
import { API_URL } from 'src/app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient) { }

  retrieveAllTodos(username) {
    return this.http.get<Todo[]>(`${API_URL}/task/all`);    
  }

  deleteTodo(username, id){
    return this.http.delete(`${API_URL}/task/delete/${id}`);
  }

 

  updateTodo(username, id, todo){
    return this.http.put(
          `${API_URL}/task/modify/${id}`
                , todo);
  }

  createTodo(username, todo){
    return this.http.post<string>(
              `${API_URL}/task/create`
                , todo);
  }

  // toggleTodoComplete(username, todo: Todo, id){
  //   todo.done = !todo.done;
  //   return this.http.put(
  //     `${API_URL}/users/${username}/todos/${id}`
  //       , todo);
  // }
}
