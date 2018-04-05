import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Place} from './entities/Place';

@Injectable()
export class PlaceService {
  private url = 'http://localhost:8080/autoservice/place';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  getPlaces(): Observable<Place[]> {
    return this.http.get<Place[]>(this.url, this.httpOptions);
  }

  addPlace(name: string) {
    return this.http.post(this.url, {params: {name: name}}, this.httpOptions);
  }
}
