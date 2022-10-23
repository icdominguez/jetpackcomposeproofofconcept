package com.example.jetpackcomposeproofofconcept.presentation

import androidx.lifecycle.ViewModel

abstract class EventLogic<E> : ViewModel() {

    // TODO: Can manage state here too to have all the view model logic unified

    abstract fun uiEvent(event: E)
}
