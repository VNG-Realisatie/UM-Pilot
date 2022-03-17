import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MpOpleidingsnaamOngecodeerdComponent} from './mp-opleidingsnaam-ongecodeerd.component';

describe('MpOpleidingsnaamOngecodeerdComponent', () => {
  let component: MpOpleidingsnaamOngecodeerdComponent;
  let fixture: ComponentFixture<MpOpleidingsnaamOngecodeerdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MpOpleidingsnaamOngecodeerdComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MpOpleidingsnaamOngecodeerdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
