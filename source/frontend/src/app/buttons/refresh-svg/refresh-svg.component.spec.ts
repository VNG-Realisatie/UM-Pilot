import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RefreshSvgComponent} from './refresh-svg.component';

describe('RefreshSvgComponent', () => {
  let component: RefreshSvgComponent;
  let fixture: ComponentFixture<RefreshSvgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RefreshSvgComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RefreshSvgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
