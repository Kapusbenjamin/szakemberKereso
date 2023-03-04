import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { CountiesService } from 'src/app/_services/counties.service';
import { JobTagsService } from 'src/app/_services/job-tags.service';

@Component({
  selector: 'app-mat-dialog',
  templateUrl: './mat-dialog.component.html',
  styleUrls: ['./mat-dialog.component.css']
})
export class MatDialogComponent implements OnInit {

  adForm = new FormGroup({
    description: new FormControl('')
  })

  constructor(private jobTagService: JobTagsService,
    private countyService: CountiesService
    ) {}

  ngOnInit(): void {

  }

}
