import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkzoekendeRequestComponent} from './werkzoekende-request.component';

describe('WerkzoekendeRequestComponent', () => {
  let component: WerkzoekendeRequestComponent;
  let fixture: ComponentFixture<WerkzoekendeRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkzoekendeRequestComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkzoekendeRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
