package dev.fenix.application.production.treatment.model;

public enum Status {
  /**
   * The workflow for the document has been initiated, but no activities have been started on the
   * document. This status is considered active. This state is usually the initial state when the
   * editorial process requires managers and approvers to allocate work for the document. The
   * equivalent task status is ‘Open’.
   */
  INITIATED,
  /**
   * Activities on this document are in progress. This usually means that the document content is
   * still being actively worked on. Additional labels can be used to describe the nature of the
   * activities being performed. This status is considered active. This might be the initial state
   * in simple editorial workflows. The equivalent task status is ‘Open’.
   */
  IN_PROGRESS,
  /**
   * The document is considered complete. Any internal post-completion operations, such as the final
   * editorial review before approval, should be conducted. This status is considered active. This
   * might be the final state in simple editorial workflows. The equivalent task status is
   * ‘Resolved’.
   */
  COMPLETE,
  /**
   * An “Approver” has checked that the document is ready and that there are no longer any
   * outstanding activities. This can be used to mark the document as ready for publishing. This is
   * a final state in the document workflow. The equivalent task status is ‘Closed’
   */
  APPROVED,
  /**
   * The workflow is quiescent and no activities are started until the process has returned to the
   * ‘In progress’ state. This state might be used in large editorial teams when a document cannot
   * be considered complete and nobody actively works on this document.
   */
  SUSPENDED,
  /**
   * Work on the document has been stopped before its normal completion. This is a final state in
   * the document workflow. The equivalent task status is ‘Closed’.
   */
  TERMINATED,

  /**
   * Work on the document has been stopped before its normal completion. This is a final state in
   * the document workflow. The equivalent task status is ‘Closed’.
   */
  CLOSED
}
