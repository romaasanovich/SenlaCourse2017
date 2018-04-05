import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class SignInService {

  httpOptions = {headers: new HttpHeaders().append('Access-Control-Allow-Origin', '*')};
  private url = 'http://localhost:8080/autoservice/login';

  constructor(private http: HttpClient) {
  }

  signIn(login: string, pass: string) {
    return this.http.post(this.url, {params: {username: login, password: pass}}, this.httpOptions);
  }
}

