import {Component, OnInit} from '@angular/core';
import {EMPTY, Observable} from 'rxjs';
import {AuthService} from 'src/app/services/auth.service';
import {OinService} from 'src/app/services/oin.service';
import {WerkzoekendeBronService} from 'src/app/services/werkzoekende-bron.service';
import {Upload} from 'src/app/util/upload';

@Component({
  selector: 'app-werkzoekende-import',
  templateUrl: './werkzoekende-import.component.html',
  styleUrls: ['./werkzoekende-import.component.css']
})
export class WerkzoekendeImportComponent implements OnInit {

  file: File | null = null;
  upload$: Observable<Upload> = EMPTY;

  selectedGemeente?: string;

  constructor(
    private werkzoekendeService: WerkzoekendeBronService,
    private oinService: OinService,
    private authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    this.selectedGemeente = this.oinService.getGemeente(this.authService.getOin());
  }

  onFileInput(files: FileList | null): void {
    if (files) {
      this.file = files.item(0);
    }
  }

  onSubmit() {
    if (this.file) {
      this.upload$ = this.werkzoekendeService.uploadFile(this.file);
    }
  }


}
