import {Component, OnInit} from '@angular/core';
import {EMPTY, Observable} from "rxjs";
import {Upload} from "../../util/upload";
import {OinService} from "../../services/oin.service";
import {AuthService} from "../../services/auth.service";
import {VacatureBronService} from "../../services/vacature-bron.service";

@Component({
  selector: 'app-vacature-import',
  templateUrl: './vacature-import.component.html',
  styleUrls: ['./vacature-import.component.css']
})
export class VacatureImportComponent implements OnInit {

  file: File | null = null;
  upload$: Observable<Upload> = EMPTY;

  selectedGemeente?: string;

  constructor(
    private vacatureService: VacatureBronService,
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
      const fileReader = new FileReader();
      fileReader.readAsText(this.file, "UTF-8");
      fileReader.onload = () => {
        this.upload$ = this.vacatureService.uploadFile(fileReader.result);
      }
      fileReader.onerror = (error) => {
        console.log(error);
      }


    }
  }


}
