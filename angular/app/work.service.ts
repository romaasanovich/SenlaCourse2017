import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Work} from './entities/Work';

@Injectable()
export class WorkService {
  private url = 'http://localhost:8080/autoservice/work';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  getWorks(): Observable<Work[]> {
    return this.http.get<Work[]>(this.url, this.httpOptions);
  }

  addWork(idMaster: number, nameService: string, price: number) {
    return this.http.post(this.url, {params: {idMaster: idMaster, nameService: name, price: price}}, this.httpOptions);
  }
}
