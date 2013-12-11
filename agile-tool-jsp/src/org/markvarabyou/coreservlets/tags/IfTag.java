package org.markvarabyou.coreservlets.tags;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * A tag that acts like an if/then/else.
 */
public class IfTag extends TagSupport {
    private boolean condition;
    private boolean hasCondition = false;

    public void setCondition(boolean condition) {
        this.condition = condition;
        hasCondition = true;
    }

    public boolean getCondition() {
        return (condition);
    }

    public void setHasCondition(boolean flag) {
        this.hasCondition = flag;
    }

    public boolean hasCondition() {
        return (hasCondition);
    }

    public int doStartTag() {
        return (EVAL_BODY_INCLUDE);
    }
}
