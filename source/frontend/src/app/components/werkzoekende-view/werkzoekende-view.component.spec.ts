import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WerkzoekendeViewComponent } from './werkzoekende-view.component';

describe('WerkzoekendeViewComponent', () => {
  let component: WerkzoekendeViewComponent;
  let fixture: ComponentFixture<WerkzoekendeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WerkzoekendeViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkzoekendeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
