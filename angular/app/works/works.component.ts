import {Component, OnInit} from '@angular/core';
import {PlaceService} from '../place.service';
import {Place} from '../entities/Place';
import {Work} from '../entities/Work';
import {WorkService} from '../work.service';

@Component({
  selector: 'app-works',
  templateUrl: './works.component.html',
  styleUrls: ['./works.component.css']
})
export class WorksComponent implements OnInit {
  selectedWork: Work;
  works: Work[];


  constructor(private workService: WorkService) {
  }

  ngOnInit() {
    this.getWorks();
  }

  getWorks(): void {
    this.workService.getWorks().subscribe(works => this.works = works);
  }

  addWork(idMaster: number, name: string, price: number) {
    return this.workService.addWork(idMaster, name, price);
  }

  onSelect(work: Work) {
    this.selectedWork = work;
  }
}
