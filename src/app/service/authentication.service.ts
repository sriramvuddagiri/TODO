import { Injectable } from '@angular/core';
import { API_URL } from '../app.constants';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import {map} from 'rxjs/operators';
import { SignUpRequest } from '../SignUpRequest';
import { User } from '../model/user';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public user:User;

  constructor(private http: HttpClient) { 
    this.user;
  }

  executeJWTAuthenticationService(user: User) {
    return this.http.post<any>(
      `${API_URL}/user/login`,user).pipe(
        map(
          data => {
            sessionStorage.setItem(AUTHENTICATED_USER, user.username);
            sessionStorage.setItem(TOKEN, `Bearer ${data.token}`);
            return data;
          }
        )
      );
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER)
  }

  getAuthenticatedToken() {
    if (this.getAuthenticatedUser())
      return sessionStorage.getItem(TOKEN)
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout(){
    sessionStorage.removeItem(AUTHENTICATED_USER)
    sessionStorage.removeItem(TOKEN)
  }
  createUserObject(username:string,password:string)
  {
    this.user.username=username;
    this.user.password=password;
    return this.user;

  }

  executeJWTSignUpService(signUpRequest: SignUpRequest) {
    
    return this.http.post<SignUpRequest>(
      `${API_URL}/user/register`,signUpRequest);
  }
}