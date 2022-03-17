import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RapportageScreenComponent } from './rapportage-screen.component';

describe('RapportageScreenComponent', () => {
  let component: RapportageScreenComponent;
  let fixture: ComponentFixture<RapportageScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RapportageScreenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RapportageScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
