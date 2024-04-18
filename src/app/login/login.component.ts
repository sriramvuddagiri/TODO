import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { User } from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username :string
  password :string
  errorMessage = 'Invalid Credentials'
  invalidLogin = false

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  handleJWTAuthLogin() {
  
    const user = new User();
    user.username = this.username;
    user.password = this.password;
    this.authenticationService.executeJWTAuthenticationService(user)
        .subscribe(
          data => {
            console.log(data)
            this.router.navigate(['home', this.username])
            this.invalidLogin = false
          },
          error => {
            console.log(error)
            this.invalidLogin = true
          }
        )
  }

}