import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { UserRegister } from '../../models/UserRegister';
import { Observable } from 'rxjs';
import { UserResponse } from '../../models/UserResponse';
import { HttpClient } from '@angular/common/http';
import { UserLogin } from '../../models/UserLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authUrl: string = environment.apiBaseUrl + '/auth';

  constructor(
    private httpClient: HttpClient
  ) { }

  register(user: UserRegister): Observable<UserResponse> {
    return this.httpClient.post<UserResponse>(this.authUrl + '/register', user, { headers : { 'Content-Type': 'application/json' }});
  }

  login(user: UserLogin): Observable<any> {
    return this.httpClient.post<any>(this.authUrl + '/login', user);
  }


}
