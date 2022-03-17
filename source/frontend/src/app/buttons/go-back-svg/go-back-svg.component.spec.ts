import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GoBackSvgComponent} from './go-back-svg.component';

describe('GoBackSvgComponent', () => {
  let component: GoBackSvgComponent;
  let fixture: ComponentFixture<GoBackSvgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GoBackSvgComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GoBackSvgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
