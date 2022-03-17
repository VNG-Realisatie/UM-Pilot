import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AddItemSvgComponent} from './add-item-svg.component';

describe('AddItemSvgComponent', () => {
  let component: AddItemSvgComponent;
  let fixture: ComponentFixture<AddItemSvgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddItemSvgComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddItemSvgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
