import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DownloadSvgComponent} from './download-svg.component';

describe('DownloadSvgComponent', () => {
  let component: DownloadSvgComponent;
  let fixture: ComponentFixture<DownloadSvgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DownloadSvgComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DownloadSvgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
