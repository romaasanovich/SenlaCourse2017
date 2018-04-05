import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Master} from './entities/Master';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';

@Injectable()
export class MasterService {
  private url = 'http://localhost:8080/autoservice/master';

  // httpOptions = {
  //   headers: new HttpHeaders({'Content-Type': 'application/json'})
  // };

  constructor(private http: HttpClient) {
  }

  getMasters(): Observable<Master[]> {
    return this.http.get<Master[]>(this.url);
  }

  addMaster(name: string) {
    return this.http.post(this.url, {params: {name: name}});
  }
}
