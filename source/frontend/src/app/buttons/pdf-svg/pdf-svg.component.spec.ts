import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PdfSvgComponent } from './pdf-svg.component';

describe('PdfSvgComponent', () => {
  let component: PdfSvgComponent;
  let fixture: ComponentFixture<PdfSvgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PdfSvgComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PdfSvgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
