import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkzoekendeScreenComponent} from './werkzoekende-screen.component';

describe('WerkzoekendeScreenComponent', () => {
  let component: WerkzoekendeScreenComponent;
  let fixture: ComponentFixture<WerkzoekendeScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkzoekendeScreenComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkzoekendeScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
