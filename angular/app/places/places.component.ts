import {Component, OnInit} from '@angular/core';
import {Place} from '../entities/Place';
import {PlaceService} from '../place.service';
import {TableModule} from 'primeng/table';


@Component({
  selector: 'app-places',
  templateUrl: './places.component.html',
  styleUrls: ['./places.component.css']
})
export class PlacesComponent implements OnInit {
  selectedPlace: Place;
  places: Place[];


  constructor(private placeService: PlaceService) { }

  ngOnInit() {
    this.getPlaces();
  }

  getPlaces(): void {
    this.placeService.getPlaces().subscribe(places => this.places = places);
  }

  addPlace(name: string) {
    return this.placeService.addPlace(name);
  }

  onSelect(place: Place) {
    this.selectedPlace = place;
  }
}
